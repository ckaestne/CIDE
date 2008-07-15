package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class formal_parameter_listEndInt1 extends formal_parameter_listEndInt {
  public formal_parameter_listEndInt1(fixed_parameter fixed_parameter, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<fixed_parameter>("fixed_parameter", fixed_parameter)
    }, firstToken, lastToken);
  }
  public formal_parameter_listEndInt1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new formal_parameter_listEndInt1(cloneProperties(),firstToken,lastToken);
  }
  public fixed_parameter getFixed_parameter() {
    return ((PropertyOne<fixed_parameter>)getProperty("fixed_parameter")).getValue();
  }
}
