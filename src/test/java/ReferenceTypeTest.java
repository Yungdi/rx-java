import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import reactive.streams.legacy.async.ReferenceTypeObject;

public class ReferenceTypeTest {

    @Test
    public void final_로선언해도_상태변경이_가능한예제() {
        final ReferenceTypeObject instance = new ReferenceTypeObject();
        Assert.assertThat(instance.getValue(), CoreMatchers.is("A"));

        instance.setValue("B");
        Assert.assertThat(instance.getValue(), CoreMatchers.is("B"));
    }

}