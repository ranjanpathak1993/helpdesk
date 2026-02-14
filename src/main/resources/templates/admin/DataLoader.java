@Component
@RequiredArgsConstructor
public class DataLoader {

    private final PermissionRepository repo;

    @PostConstruct
    public void load() {
        List<String> perms = List.of(
            "DASHBOARD_VIEW",
            "USER_CREATE",
            "REPORT_VIEW"
        );

        for (String p : perms) {
            repo.findByCode(p)
                .orElseGet(() -> repo.save(new Permission(null, p)));
        }
    }
}
