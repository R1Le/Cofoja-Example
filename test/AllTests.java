import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({ KostimografTest.class,
                 PozoristeTest.class,
                 PredstavaTest.class,
                 RepertoarTest.class })

class AllTests {
    
}
