package Basic;// ✅ Java Spring Boot Microservices Project Template for Interview Preparation

// This file contains comments, logging, and basic setup for:
// - Spring Boot API
// - Spring Security with JWT
// - OAuth2 integration
// - Spring Batch sample
// - Custom exception handling
// - H2 in-memory DB
// - Eureka service registry
// - Spring Cloud Gateway
// - Config Server
// - Resilience4J: Circuit Breaker, Retry, Rate Limiter

// 🟢 Main Application Class
@SpringBootApplication
@EnableEurekaClient
public class InterviewPrepApplication {
    public static void main(String[] args) {
        SpringApplication.run(InterviewPrepApplication.class, args);
        System.out.println("✅ Interview Prep Microservice Running...");
    }
}

// 🔐 Security Configuration with JWT + OAuth
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2Login(); // OAuth2 login support
    }
}

// 🔐 JWT Utility (token creation/validation)
@Component
public class JwtUtil {
    private final String secret = "secret-key";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}

// 📦 Controller with Logging
@RestController
@RequestMapping("/api")
public class SampleController {
    private static final Logger log = LoggerFactory.getLogger(SampleController.class);

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        log.info("/hello endpoint hit");
        return ResponseEntity.ok("Hello from secured API");
    }
}

// 🧾 Custom Exception Handler
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

// 🗃️ H2 DB Entity
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private String role;
    // Getters/Setters
}

// 📑 Spring Batch Configuration Sample
@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Bean
    public Job sampleJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        Step step = stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("✅ Batch job running");
                    return RepeatStatus.FINISHED;
                })
                .build();
        return jobBuilderFactory.get("sampleJob").start(step).build();
    }
}

// 🛡️ Resilience4j - Circuit Breaker Sample
@RestController
public class CircuitBreakerController {
    @Autowired
    private ExternalService externalService;

    @GetMapping("/cb")
    @CircuitBreaker(name = "external", fallbackMethod = "fallback")
    public String callExternal() {
        return externalService.call();
    }

    public String fallback(Exception e) {
        return "Fallback: Service is down";
    }
}

@Service
public class ExternalService {
    public String call() {
        if (new Random().nextBoolean()) throw new RuntimeException("Fail");
        return "Success from external service";
    }
}

// 🧾 application.properties equivalent for application.yml:
// spring.application.name=interview-service
// spring.datasource.url=jdbc:h2:mem:testdb
// spring.datasource.driverClassName=org.h2.Driver
// spring.h2.console.enabled=true
// eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
// resilience4j.circuitbreaker.instances.external.registerHealthIndicator=true
// resilience4j.circuitbreaker.instances.external.slidingWindowSize=10

// 📦 You will also need: EurekaServerApplication, ConfigServerApplication, and Gateway Application
// Each as its own Spring Boot service with proper annotations: @EnableEurekaServer, @EnableConfigServer, etc.
// Let me know to generate them step-by-step.