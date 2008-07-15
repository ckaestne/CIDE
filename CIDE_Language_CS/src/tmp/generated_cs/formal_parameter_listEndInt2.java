package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class formal_parameter_listEndInt2 extends formal_parameter_listEndInt {
  public formal_parameter_listEndInt2(parameter_array parameter_array, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<parameter_array>("parameter_array", parameter_array)
    }, firstToken, lastToken);
  }
  public formal_parameter_listEndInt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new formal_parameter_listEndInt2(cloneProperties(),firstToken,lastToken);
  }
  public parameter_array getParameter_array() {
    return ((PropertyOne<parameter_array>)getProperty("parameter_array")).getValue();
  }
}
