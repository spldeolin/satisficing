#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.dto.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Deolin 2023-04-09
 */
@Data
@Accessors(chain = true)
public class DemoRespDto {

    private String name;

}