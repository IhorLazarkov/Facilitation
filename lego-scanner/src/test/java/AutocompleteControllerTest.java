import org.junit.jupiter.api.Test;
import org.legoscanner.web.controllers.AutocompleteController;
import org.legoscanner.web.serviceimplementation.DefaultFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = {
        AutocompleteController.class,
        DefaultFetcher.class
})
@AutoConfigureMockMvc
public class AutocompleteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testAutocomplete() throws Exception {
        this.mvc.perform(get("/autocomplete").param("name", "Chain"))
                .andExpect(status().isOk());
    }
}
