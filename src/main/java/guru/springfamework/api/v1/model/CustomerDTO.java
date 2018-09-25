package guru.springfamework.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {
    @ApiModelProperty(value = "this is first name", required = true)
    String firstname;
    String lastname;
    String customer_url;
}
