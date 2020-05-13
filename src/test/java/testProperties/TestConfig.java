package testProperties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties" })
public interface TestConfig extends Config {
    @DefaultValue("chrome")
    String browser();

    /* use value = remote for running test on remote server */
    @DefaultValue("local")
    String runTestStrategy();
}
