#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.dto.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author ${author} ${today}
 */
@Data
@Accessors(chain = true)
public class SampleRespDto {

    private String greeting;

}