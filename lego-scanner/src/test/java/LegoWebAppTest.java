import info.facilitator.bean.LegoBean;
import info.facilitator.bean.LegoBeanBuilder;
import org.junit.jupiter.api.Test;
import org.legoscanner.web.controllers.GenericController;
import org.legoscanner.web.serviceimplementation.DateFiltration;
import org.legoscanner.web.serviceimplementation.DefaultFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {
        GenericController.class,
        DefaultFetcher.class
})
@AutoConfigureMockMvc
public class LegoWebAppTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testHomePage() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("legos"))
                .andExpect(view().name("home"))
                .andReturn();

        ModelAndView mv = result.getModelAndView();

        assertThat(mv.getModel()).isNotNull();
        assertThat(mv.getModel()).isNotEmpty();
    }

    @Test
    public void testGetItem() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/getItem").requestAttr("name", "test"))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("legos"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

        DateFiltration filtration1 = new DateFiltration("2021-09-09");
        List<LegoBean> result = legos.stream().filter(filtration1::fromDateOnward).collect(Collectors.toList());
        assertThat(result.size()).isEqualTo(2);

        DateFiltration filtration2 = new DateFiltration("2021-09-10");
        result = legos.stream().filter(filtration2::fromDateBackward).collect(Collectors.toList());
        assertThat(result.size()).isEqualTo(2);

        result = legos.stream()
                .filter(filtration1::fromDateOnward)
                .filter(filtration2::fromDateBackward)
                .collect(Collectors.toList());
        assertThat(result.size()).isEqualTo(2);
    }
}
