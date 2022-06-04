package capstone4.Cosi.servieces;

import capstone4.Cosi.domains.Member;
import capstone4.Cosi.repositories.MemberRepository;
import capstone4.Cosi.domains.ConfirmationToken;
import capstone4.Cosi.exceptions.MemberNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService implements UserDetailsService {

    private final static String MEMBER_NOT_FOUND_MSG =
            "주어진 계정 %s 은 미등록된 계정입니다.";

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailValidator emailValidator;

    public MemberService(BCryptPasswordEncoder bCryptPasswordEncoder,
                         ConfirmationTokenService confirmationTokenService,
                         MemberRepository memberRepository,
                         EmailValidator emailValidator) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
        this.memberRepository = memberRepository;
        this.emailValidator = emailValidator;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(MEMBER_NOT_FOUND_MSG, email)));
    }

    @Transactional
    public String isEmailDuplicated(String email){
        Optional<Member> member = memberRepository.findMemberByEmail(email);
        if(member.isPresent()){
            return "이미 사용 중인 이메일입니다.";
        } else {
            if(emailValidator.test(email)){
                return "사용 가능한 이메일입니다.";
            } else {
                return "잘못된 이메일 형식입니다.";
            }
        }
    }

    @Transactional
    public String signUp(Member member){
        //유효성 검사
        boolean memberExists = memberRepository
                .findByEmail(member.getEmail())
                .isPresent();

        if(memberExists){
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("이미 사용 중인 이메일입니다.");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(member.getPassword());

        member.setPassword(encodedPassword);

        memberRepository.save(member);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                member
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }

    public int enableMember(String email){
        return memberRepository.enableMember(email);
    }
}
