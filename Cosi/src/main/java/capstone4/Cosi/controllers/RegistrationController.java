package capstone4.Cosi.controllers;

import capstone4.Cosi.VOs.RegistrationRequest;
import capstone4.Cosi.servieces.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@ModelAttribute RegistrationRequest request, Model model){

        try{
            registrationService.register(request);
            model.addAttribute("msg", "등록한 이메일로 인증 요청 메일이 전송되었습니다.<br>15분 내에 인증 링크를 클릭해주십시오.");
        } catch (Exception e){
            model.addAttribute("msg", "계정 등록 실패!: " + e.getMessage());
        }

        return "/requestEmailConfirmation";
    }

    @GetMapping(path = "confirmed")
    public String confirmed(@RequestParam("token") String token, Model model) {
        String result;
        try {
            result = registrationService.confirmToken(token);
            model.addAttribute("msg", "인증이 완료되었습니다. 로그인 후 서비스 이용이 가능합니다.<br><a href='/login'>로그인</a>");
        } catch (Exception e) {
            model.addAttribute("msg", "유효하지 않거나 만료된 인증입니다. <br>사유: " + e.getMessage());
        }
        return "/requestEmailConfirmation";
    }
}
