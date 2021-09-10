import info.facilitator.bean.LegoBean;
import info.facilitator.bean.LegoBeanBuilder;
import org.junit.jupiter.api.Test;
import org.legoscanner.web.serviceimplementation.DateFiltration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class DataCollectionTest {


    @Test
    public void testTimeFiltration() throws ParseException {
        final List<LegoBean> legos = Arrays.asList(
                new LegoBeanBuilder()
                        .createBean()
                        .setSalePrice("10.99")
                        .setLegoName("Test Lego")
                        .build(),
                new LegoBeanBuilder()
                        .createBean()
                        .setPrice("9.99")
                        .setLegoName("Lego 1 Some Lego")
                        .build()
        );

        final SimpleDateFormat dateFormatter = new SimpleDateFormat(DateFiltration.DateFormat.DATE_FORMAT.getValue());
        Date date = dateFormatter.parse("2021-09-09");
        legos.get(0).setDate(dateFormatter.format(date));
        date = dateFormatter.parse("2021-09-10");
        legos.get(1).setDate(dateFormatter.format(date));
        assertThat(legos.size()).isEqualTo(2);

        Predicate<LegoBean> filtrationOnward = new DateFiltration("2021-09-09")::fromDateOnward;
        Predicate<LegoBean> filtrationBackward = new DateFiltration("2021-09-10")::fromDateBackward;

        List<LegoBean> result = legos.stream().filter(filtrationOnward).collect(Collectors.toList());
        assertThat(result.size()).isEqualTo(2);

        result = legos.stream().filter(filtrationBackward).collect(Collectors.toList());
        assertThat(result.size()).isEqualTo(2);

        result = legos.stream()
                .filter(filtrationOnward)
                .filter(filtrationBackward)
                .collect(Collectors.toList());
        assertThat(result.size()).isEqualTo(2);
    }
}
