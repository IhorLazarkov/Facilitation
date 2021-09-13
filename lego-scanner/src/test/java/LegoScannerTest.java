import info.facilitator.bean.LegoBean;
import info.facilitator.bean.LegoBeanBuilder;
import info.facilitator.bean.dao.LegoDAO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LegoScannerTest {

    @Test
    public void testIsToday() {
        LegoBean testLego = new LegoBeanBuilder().createBean()
                .setLegoName("TestLego")
                .setPrice("100.00")
                .setSalePrice("99.99")
                .build();

        assertThat(LegoDAO.isToday().test(testLego)).isEqualTo(true);
    }
}
