package capstone4.Cosi;

import capstone4.Cosi.domains.*;
import capstone4.Cosi.repositories.CoinRepository;
import capstone4.Cosi.repositories.MemberRepository;
import capstone4.Cosi.repositories.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CosiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosiApplication.class, args);
	}

	//테스트 용
	@Bean
	CommandLineRunner commandLineRunner(
		MemberRepository memberRepository,
		CoinRepository coinRepository,
		OrderRepository orderRepository
	){
		return args -> {
//			System.out.println("***** 멤버 생성 & 삽입 *****");
//			ArrayList<Member> members = new ArrayList<>();
//			members.add(new Member("32174842@dankook.ac.kr", "1234", "한승엽", UserRole.USER));
//			members.add(new Member("st.jhonhans@gmail.com", "2345", "홍길동", UserRole.USER));
//			members.add(new Member("street4727@naver.com", "3456", "고길동", UserRole.USER));
//			memberRepository.saveAll(members);

			System.out.println("***** 코인 생성 & 삽입 *****");
			ArrayList<Coin> coins = new ArrayList<>();
			coins.add(new Coin("BTC", "비트코인"));
			coins.add(new Coin("ETH", "이더리움"));
			coins.add(new Coin("USDT", "테더"));
			coinRepository.saveAll(coins);

//			System.out.println("***** 자산(-> 멤버) 생성 & 삽입 *****");
//			ArrayList<Asset> assets = new ArrayList<>();
//			assets.add(new Asset(members.get(0), 100000000.0));
//			assets.add(new Asset(members.get(1), 200000000.0));
//			assets.add(new Asset(members.get(2), 50000000.0));
//			members.get(0).setAsset(assets.get(0));
//			members.get(1).setAsset(assets.get(1));
//			members.get(2).setAsset(assets.get(2));
//			memberRepository.saveAll(members);
//
//			members.get(0).addWallet(new Wallet(members.get(0), coins.get(0), 10.0, 3000.0));
//			members.get(0).addWallet(new Wallet(members.get(0), coins.get(1), 8.0, 502.54));
//			members.get(1).addWallet(new Wallet(members.get(1), coins.get(1), 3.5, 488.22));
//			members.get(1).addWallet(new Wallet(members.get(1), coins.get(2), 2.99, 321.0));
//			members.get(2).addWallet(new Wallet(members.get(2), coins.get(2), 0.123, 312.5));
//			members.get(2).addWallet(new Wallet(members.get(2), coins.get(0), 1.1, 2998.2));
//
//			System.out.println("***** 지갑 삽입 반영 *****");
//			memberRepository.saveAll(members);
//
//			System.out.println("***** 1개의 select를 기대함 *****");
//			memberRepository.findById(1L);
//
////			System.out.println("\n\n************** 멤버를 통한 주문 저장 *************\n\n");
////			Order order1 = new Order(
////					true, OrderType.LIMIT, LocalDateTime.now(), OrderState.UNFILLED,
////					1.32, 37972000.0, null, 1.32 * 37972000.0,
////					(1.32 * 37972000.0) * 0.0005, 0.0005, members.get(0), coins.get(0)
////			);
////			members.get(0).addOrder(order1);
////			memberRepository.save(members.get(0));
////
////			System.out.println("\n\n************** 멤버에 반영되었는지 확인 *************\n");
////			members.get(0).getOrders().forEach(System.out::println);
////			System.out.println("***** OrderRepo를 통하지 않고, MemberRepo를 통해서 간접적으로 저장하면, order1객체의 id가 null이 되므로" +
////					"\n추후에 order1 객체를 수정하여 save 했을 때 별개의 order로 등록되는 문제가 발생함" +
////					"\n따라서 order를 저장할 때에는 아예 orderRepo만을 통해서 하는 것이 정신 건강에 좋을 것임 이를 통해서 알 수 있는 것은" +
////					"\n독립적인 auto_increment id를 가지고 있는 테이블에 대해서는 별개의 repository 인터페이스를 정의하고 항상 해당" +
////					"\nrepo 인터페이스를 통해서만 save해야한다는 사실임. 다시 한 번 말하지만 그렇지 않을 경우 같은 엔티티가 의도치 한게 별개의 id를 가진 채" +
////					"\n데이터베이스에 저장되게 됨, 하지만 이 때 member에서 cascade 설정을 어떻게 할 것인지는 여전히 고민이 되긴 함");
////			System.out.println("이제 order1을 OrderRepo를 통해서 save하면 별개의 테이블로 저장됨을 확인 가능함");
////			members.get(0).removeOrder(order1);
////			orderRepository.save(order1);
////			members.get(0).addOrder(order1);
////			orderRepository.findAll().forEach(System.out::println);
//
//			System.out.println("\n\n************** 독립적인 주문 저장 *************\n");
//			Order order1 = new Order(
//					true, OrderType.LIMIT, LocalDateTime.now(), OrderState.UNFILLED,
//					1.32, 37972000.0, null, 1.32 * 37972000.0,
//					(1.32 * 37972000.0) * 0.0005, 0.0005, members.get(0), coins.get(0)
//			);
//			Order order2 = new Order(
//					false, OrderType.STOP_LIMIT, LocalDateTime.now().minusDays(2), OrderState.UNFILLED,
//					11.2, 37972000.0, 38000000.0, 11.2 * 38000000.0,
//					(11.2 * 38000000.0) * 0.0000, 0.0000, members.get(1), coins.get(1)
//			);
//			orderRepository.saveAll(List.of(order1, order2));
//			members.get(0).addOrder(order1);
//			members.get(1).addOrder(order2);
//
//			System.out.println("\n\n************** order1 체결 *************\n");
//			System.out.println("***** " + order1.getId());
//			FilledOrder filledOrder = new FilledOrder(
//				order1, LocalDateTime.now(), 1.32, 1.32 * 37972000.0, 37972000.0, 0.0, 0.0
//			);
//			order1.setOrderState(OrderState.FILLED);
//			System.out.println("***** " + order1.getId());
//			order1.setFilledOrder(filledOrder);
//			System.out.println("***** " + order1.getId());
//			orderRepository.save(order1);
//			System.out.println("***** " + order1.getId());
//
//			System.out.println("\n\n************** 체결 후 멤버를 통한 주문 확인 *************\n");
////			memberRepository.findById(2L).ifPresent(m -> {
////				m.getOrders().forEach(System.out::println);
////			});
//			System.out.println("memberRepo를 통한 주문 확인은 getOrder를 통해서 할 수 없다. 영속성 컨텍스튼지 뭔지에서 이미 내려간 상태라서 그렇단다" +
//					"\n 암튼 그걸 하려면 memberRepo에서 member 엔티티를 가져온 다음에 id를 뽑고 그 id로 다시 orderRepo에 요청하는 식으로 해야한다.");
//
//
//
//			//join
//			memberRepository.findById(1L).ifPresent(member -> {
//				System.out.println(member);
//				System.out.println(member.getAsset());
//				//member.getWallets().forEach(System.out::println);		//ERROR!!!
//			});
//
//			System.out.println("*******************");
//
//			//join fetch
//			memberRepository.findMemberByIdWithWallets(1L).forEach(member -> {
//				member.getWallets().forEach(System.out::println);
//			});
		};
	}
}
