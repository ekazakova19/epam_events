package testProperties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties" })
public interface TestConfig extends Config {
    @DefaultValue("chrome")
    String browser();

    @DefaultValue("remote")
    String runTestStrategy();
}
