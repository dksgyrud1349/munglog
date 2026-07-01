package com.munglog.munglog.service;

import com.munglog.munglog.config.ErrorCode;
import com.munglog.munglog.config.Response;
import com.munglog.munglog.dto.CreateProFileRequest;
import com.munglog.munglog.dto.UpdateProFileRequest;
import com.munglog.munglog.entity.ProFile;
import com.munglog.munglog.enums.IsDeleted;
import com.munglog.munglog.exception.ProFileException;
import com.munglog.munglog.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.security.SecureRandom;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {

    public final ProfileRepository profileRepository;

    public final FileService fileService;

    private static final SecureRandom random = new SecureRandom();
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Transactional
    public Response createDog(CreateProFileRequest createProFileRequest) {

        try {

            // 반려견 번호 검증
            String dogId = this.generateDogUuid();

            // 이미지
            MultipartFile profileImage = createProFileRequest.getProfileImgUrl();
            String imageUrl = fileService.upload(profileImage);

            log.info("profileImage = {}", profileImage);
            log.info("isEmpty = {}", profileImage.isEmpty());

            ProFile proFile = ProFile.builder()
                        .dogId(dogId)
                        .dogNm(createProFileRequest.getDogNm())
                        .breed(createProFileRequest.getBreed())
                        .age(createProFileRequest.getAge())
                        .weight(createProFileRequest.getWeight())
                        .membershipNo("asdf")
                        .isDeleted(IsDeleted.N)
                        .profileImgUrl(imageUrl)
                        .build();

            profileRepository.save(proFile);

            return Response.result("200", "프로필 저장에 성공하였습니다.");

        } catch (Exception e) {
            return Response.result(ErrorCode.FAIL_SAVE_PROFILE);
        }
    }

    public String generateDogUuid() {

        String dogUuId = "";

        while(true) {
            // 랜덤 회원번호 생성
            dogUuId = this.createRandomStr();

            if(dogUuId.isEmpty()) {
                break;
            }
            // 반려견 아이디 중복 체크
            boolean checkDuplicateMembershipNo = this.isValidDogUuId(dogUuId);
            if(!checkDuplicateMembershipNo) {
                break;
            }
        }
        return dogUuId;
    }

    public String createRandomStr() {
        String randomStr = "";

        try {

            List<Character> chars = new ArrayList<>();

            // 영문 4개 생성
            for (int i = 0; i < 3; i++) {
                chars.add(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
            }

            // 숫자 5개 생성
            for (int i = 0; i < 9; i++) {
                chars.add((char) ('0' + random.nextInt(10)));
            }

            // 섞기
            Collections.shuffle(chars, random);

            // String으로 변환
            StringBuilder stringBuilder = new StringBuilder();
            for (char c : chars) {
                stringBuilder.append(c);
            }
            randomStr = stringBuilder.toString();
        } catch (Exception e) {
            randomStr = "";
        }
        return randomStr;
    }

    public boolean isValidDogUuId(String dogId) {
        boolean duplicateFlag = false;
        Optional<ProFile> proFile = profileRepository.findById(dogId);
        if(proFile.isPresent()) {
            // 중복 발생
            duplicateFlag = true;
        }
        return duplicateFlag;
    }

    public List<ProFile> dogList() {
        return profileRepository.dogList("asdf", IsDeleted.N);
    }

    public Response findDog(String dogId) {

        ProFile proFile;

        try {

            String membershipNo = "asdf";
            proFile = profileRepository.dogInfo(membershipNo, IsDeleted.N, dogId);

            if(proFile == null) {
                throw new ProFileException(ErrorCode.FAIL_FIND_BY_ID);
            }

        } catch (ProFileException pe) {
            return Response.result(pe.getCode(), pe.getMessage());
        }

        return Response.resultData(proFile, "200", "상세 정보 조회 성공");
    }

    @Transactional
    public Response updateDog(UpdateProFileRequest request) {

        try {

            String membershipNo = "asdf";
            ProFile proFile = profileRepository.dogInfo(membershipNo, IsDeleted.N, request.getDogId());

            if(proFile == null) {
                throw new ProFileException(ErrorCode.FAIL_FIND_BY_ID);
            }

            proFile.setDogNm(request.getDogNm());
            proFile.setBreed(request.getBreed());
            proFile.setAge(request.getAge());
            proFile.setWeight(request.getWeight());

            MultipartFile profileImage = request.getProfileImgUrl();

            if (profileImage != null && !profileImage.isEmpty()) {

                // 기존 파일 삭제
                fileService.delete(proFile.getProfileImgUrl());

                // 새 파일 업로드
                String imageUrl = fileService.upload(profileImage);

                proFile.setProfileImgUrl(imageUrl);
            }

        } catch (ProFileException pe) {
            return Response.result(pe.getCode(), pe.getMessage());
        }

        return Response.result("200", "반려견 프로필 수정이 완료되었습니다.");
    }

    @Transactional
    public Response deleteDog(String id) {
        try {

            String membershipNo = "asdf";
            ProFile proFile = profileRepository.dogInfo(membershipNo, IsDeleted.N, id);

            if(proFile == null) {
                throw new ProFileException(ErrorCode.FAIL_FIND_BY_ID);
            }

            proFile.setIsDeleted(IsDeleted.Y);

            fileService.delete(proFile.getProfileImgUrl());

            proFile.setProfileImgUrl("");

        } catch (ProFileException pe) {
            return Response.result(pe.getCode(), pe.getMessage());
        }

        return Response.result("200", "반려견 프로필 삭제가 완료되었습니다.");
    }
}
