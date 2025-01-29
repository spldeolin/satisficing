#set($year = $str.getClass().forName("java.time.Year").getMethod("now").invoke(null).toString())
package ${package}.api.dto.req;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ${author} ${today}
 */
@Data
@Accessors(chain = true)
public class SampleReqDto {

    @NotEmpty
    private String name;

}