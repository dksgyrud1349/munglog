package com.munglog.munglog.controller;

import com.munglog.munglog.config.Response;
import com.munglog.munglog.dto.CreateProFileRequest;
import com.munglog.munglog.dto.UpdateProFileRequest;
import com.munglog.munglog.entity.ProFile;
import com.munglog.munglog.enums.ProFileMode;
import com.munglog.munglog.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users/dogs")
@RequiredArgsConstructor
public class ProfileController {

    public final ProfileService profileService;

    // 반려견 목록
    @GetMapping("/list")
    public String getDogList(Model model) {
        List<ProFile> dogList = profileService.dogList();
        model.addAttribute("dogList", dogList);
        return "/dog/list";
    }

    // 반려견 정보 등록 폼
    @GetMapping("/regForm")
    public String dogForm(Model model) {
        model.addAttribute("mode", ProFileMode.CREATE);
        return "/dog/form";
    }

    // 반려견 정보 저장
    @PostMapping("/create")
    public String createDog(@ModelAttribute CreateProFileRequest createProFileRequest,
                            RedirectAttributes redirectAttributes) {
        Response createDogRes = profileService.createDog(createProFileRequest);
        redirectAttributes.addFlashAttribute("message", createDogRes.getMessage());

        return "redirect:/users/dogs/list";
    }

    // 반려견 정보 수정 폼
    @GetMapping("/editForm/{id}")
    public String getDogDetail(@PathVariable("id") String id,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        Response dogInfo = profileService.findDog(id);

        if(dogInfo.getCode().equals("200")) {
            model.addAttribute("proFile", dogInfo.getData());
            model.addAttribute("mode", ProFileMode.EDIT);
            return "/dog/form";
        } else {
            redirectAttributes.addFlashAttribute("message", dogInfo.getMessage());
            return "redirect:/users/dogs/list";
        }
    }

    // 반려견 정보 수정
    @PostMapping("/update")
    public String updateProFile(@ModelAttribute UpdateProFileRequest updateProFileRequest,
                                RedirectAttributes redirectAttributes) {
        Response updateProFile = profileService.updateDog(updateProFileRequest);

        redirectAttributes.addFlashAttribute("message", updateProFile.getMessage());
        return "redirect:/users/dogs/list";
    }

    // 반려견 정보 삭제
    @GetMapping("/delete/{id}")
    public String deleteProFile(@PathVariable("id") String id,
                                RedirectAttributes redirectAttributes) {

        Response deleteProFile = profileService.deleteDog(id);

        redirectAttributes.addFlashAttribute("message", deleteProFile.getMessage());
        return "redirect:/users/dogs/list";
    }
}
