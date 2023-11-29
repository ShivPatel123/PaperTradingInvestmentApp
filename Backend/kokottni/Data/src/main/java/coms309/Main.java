package coms309;

import coms309.Stocks.StockPurchasedRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.ComponentScan;

import coms309.Stocks.Stock;
import coms309.Stocks.StockRepository;
import coms309.Users.User;
import coms309.Users.UserRepository;
import coms309.Users.FriendGroup;
import coms309.Users.FriendGroupRepository;
import coms309.chat.ChatSocket;
import coms309.chat.Message;
import coms309.chat.MessageRepository;



// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
@ComponentScan(basePackages = {"coms309", "coms309.Users", "coms309.chat"})
public class Main {
    public static void main(String[] args) {SpringApplication.run(Main.class, args);}
    @Bean
    CommandLineRunner initUser(
            UserRepository userRepository,
            StockRepository stockRepository,
            StockPurchasedRepository spRepository,
            FriendGroupRepository friendGroupRepository
    ) {
        return args -> {



            User user1 = new User(1L, 1987654, "Nick", "kokottni@iastate.edu", "oldenough", "user", "Password");
            user1.setPrivilege('a');
            User user2 = new User(2L, 34578, "Josh", "jwhit@iastate.edu", "oldenough", "username", "password");
            User user3 = new User(3L, 6543, "Shiv", "shiv@iastate.edu", "nah", "username2", "password");
            User user4 = new User(4L, 7765, "Skyler", "sky@iastate.edu", "yup", "user3", "passman");
            Stock stock1 = new Stock(1L, "TSLA", "Tesla", 101.23, -2.13);
            Stock stock2 = new Stock(2L, "INTC", "Intel", 67.12, 1.34);
            Stock stock3 = new Stock(3L, "GGL", "Google", 213.56, 3.14);
            Stock stock4 = new Stock(4L, "EXAS", "Exact Sciences", 67.34, -2.13);

            "NVDA"	"NVIDIA Corporation" 	478.21	-4.21
            "META"	"Meta Platforms, Inc". Class A Common Stock	$338.99	4.29	1.282%	871,157,767,521
            BRK/A	Berkshire Hathaway Inc.	$546,868.98999999999	-576.06	-0.105%	804,375,378,797
            BRK/B	Berkshire Hathaway Inc.	$360.05	-1.29	-0.357%	794,382,409,129
            TSLA	Tesla, Inc. Common Stock	$246.72	10.64	4.507%	784,303,485,588
            HSBC	HSBC Holdings, plc. Common Stock	$38.59	-0.02	-0.052%	759,952,870,000
            LLY	Eli Lilly and Company Common Stock	$591.60	0.07	0.012%	561,610,161,409
            TSM	Taiwan Semiconductor Manufacturing Company Ltd.	$97.98	0.77	0.792%	508,131,735,494
            UNH	UnitedHealth Group Incorporated Common Stock (DE)	$540.53	-3.06	-0.563%	499,949,868,625
            V	Visa Inc.	$252.94	-1.20	-0.472%	464,308,388,528
            NVO	Novo Nordisk A/S Common Stock	$101.43	-2.44	-2.349%	454,199,652,797
            JPM	JP Morgan Chase & Co. Common Stock	$153.54	0.35	0.228%	443,885,420,677
            SHEL	Shell PLC American Depositary Shares (each representing two (2) Ordinary Shares)	$66.00	0.19	0.289%	441,580,326,000
            WMT	Walmart Inc. Common Stock	$158.64	1.87	1.193%	426,989,689,164
            XOM	Exxon Mobil Corporation Common Stock	$103.90	-0.06	-0.058%	411,747,168,355
            AVGO	Broadcom Inc. Common Stock	$946.35	-3.89	-0.409%	390,592,244,210
            MA	Mastercard Incorporated Common Stock	$409.01	0.04	0.01%	383,559,786,710
            JNJ	Johnson & Johnson Common Stock	$151.63	0.35	0.231%	365,015,657,151
            PG	Procter & Gamble Company (The) Common Stock	$152.29	1.05	0.694%	358,930,201,073
            ORCL	Oracle Corporation Common Stock	$116.24	-0.23	-0.197%	318,476,793,040
            BHP	BHP Group Limited American Depositary Shares (Each representing two Ordinary Shares)	$61.64	0.33	0.538%	312,170,157,322
            HD	Home Depot, Inc. (The) Common Stock	$313.34	2.42	0.778%	311,855,346,199
            ADBE	Adobe Inc. Common Stock	$623.32	4.05	0.654%	283,797,596,000
            CVX	Chevron Corporation Common Stock	$145.51	1.15	0.797%	274,686,308,244
            ASML	ASML Holding N.V. New York Registry Shares	$675.99	-12.39	-1.80%	266,738,495,942
            COST	Costco Wholesale Corporation Common Stock	$594.00	-0.90	-0.151%	262,987,899,768
            TM	Toyota Motor Corporation Common Stock	$187.16	0.55	0.295%	257,874,691,623
            MRK	Merck & Company, Inc. Common Stock (new)	$100.18	-1.25	-1.232%	253,858,432,555
            KO	Coca-Cola Company (The) Common Stock	$58.58	0.12	0.205%	253,265,580,990
            ABBV	AbbVie Inc. Common Stock	$138.08	-1.01	-0.726%	243,785,407,092
            NGG	National Grid Transco, PLC National Grid PLC (NEW) American Depositary Shares	$65.70	0.29	0.443%	242,313,299,330
            BAC	Bank of America Corporation Common Stock	$29.53	-0.03	-0.101%	233,692,506,373
            PEP	PepsiCo, Inc. Common Stock	$168.86	0.54	0.321%	232,159,528,454
            ACN	Accenture plc Class A Ordinary Shares (Ireland)	$332.56	0.13	0.039%	221,189,668,336
            CRM	Salesforce, Inc. Common Stock	$224.92	0.13	0.058%	218,847,160,000
            NFLX	Netflix, Inc. Common Stock	$479.00	-0.17	-0.035%	209,648,561,451
            NVS	Novartis AG Common Stock	$97.00	-0.74	-0.757%	205,602,078,529
            MCD	McDonald's Corporation Common Stock	$282.09	0.25	0.089%	204,611,733,243
            LIN	Linde plc Ordinary Shares	$410.73	-2.07	-0.501%	199,159,069,315
            AMD	Advanced Micro Devices, Inc. Common Stock	$122.01	-0.64	-0.522%	197,107,019,691
            BABA	Alibaba Group Holding Limited American Depositary Shares each representing eight Ordinary share	$76.74	-0.79	-1.019%	196,895,824,902
            CSCO	Cisco Systems, Inc. Common Stock (DE)	$47.85	-0.08	-0.167%	194,437,311,097
            SAP	SAP SE ADS	$156.30	1.36	0.878%	192,015,211,462
            TMO	Thermo Fisher Scientific Inc Common Stock	$485.92	-4.21	-0.859%	187,746,038,220
            INTC	Intel Corporation Common Stock	$44.23	0.15	0.34%	186,473,680,000

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);
            stockRepository.save(stock1);
            stockRepository.save(stock2);
            stockRepository.save(stock3);
            stockRepository.save(stock4);

            user1.setStock(stock1, 2, stock1.getId());
            stock1.setUser(user1, 2, stock1.getId());
            user2.setStock(stock2, 3, stock2.getId());
            stock2.setUser(user2, 3, stock2.getId());
            user3.setStock(stock3, 1, stock3.getId());
            stock3.setUser(user3, 1, stock3.getId());
            user4.setStock(stock4, 4, stock4.getId());
            stock4.setUser(user4, 4, stock4.getId());
            spRepository.save(user1.getStocks().get(0));
            spRepository.save(user2.getStocks().get(0));
            spRepository.save(user3.getStocks().get(0));
            spRepository.save(user4.getStocks().get(0));
        };
    }
}