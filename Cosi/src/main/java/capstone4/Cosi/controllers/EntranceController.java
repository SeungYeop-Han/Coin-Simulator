package capstone4.Cosi.controllers;

import capstone4.Cosi.servieces.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EntranceController {

    MemberService memberService;

    @Autowired
    public EntranceController(MemberService memberService){
        this.memberService = memberService;
    }

//    시작 페이지
    @RequestMapping(value = {"/", "/home"})
    public String welcome(){
        return "/home";
    }

//    로그인 폼 요청
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/login";
    }

//    회원가입 폼 요청
    @GetMapping("/registration")
    public String signUp(){
        return "/registration";
    }

    //    이메일 중복 여부 확인 요청
    @GetMapping("registration/isEmailDuplicated")
    @ResponseBody
    public String signUp(@RequestParam("email") String email) {
        System.out.println(email);
        if(email.isEmpty()){
            return "이메일을 입력해주세요.";
        } else{
            return memberService.isEmailDuplicated(email);
        }
    }

//    회원가입 결과 페이지


//    거래소 패이지
    @GetMapping("/market")
    public String enterMarket(){
        return "/market";
    }

}
