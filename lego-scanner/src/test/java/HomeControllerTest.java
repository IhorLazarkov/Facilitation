import org.junit.jupiter.api.Test;
import org.legoscanner.web.controllers.HomeController;
import org.legoscanner.web.serviceimplementation.DefaultFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {
        HomeController.class,
        DefaultFetcher.class
})
@AutoConfigureMockMvc
public class HomeControllerTest {

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
            mvc.perform(MockMvcRequestBuilders.get("/getItemByName").param("name", "test"))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("legos"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDateFiltration() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/filterByDate")
                            .param("from", "2000-01-01")
                            .param("to", "2999-01-01")
                    )
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("legos"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
