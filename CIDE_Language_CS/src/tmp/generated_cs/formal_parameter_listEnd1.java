package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class formal_parameter_listEnd1 extends formal_parameter_listEnd {
  public formal_parameter_listEnd1(fixed_parameter fixed_parameter, ArrayList<fixed_parameterEnd> fixed_parameterEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<fixed_parameter>("fixed_parameter", fixed_parameter),
      new PropertyZeroOrMore<fixed_parameterEnd>("fixed_parameterEnd", fixed_parameterEnd)
    }, firstToken, lastToken);
  }
  public formal_parameter_listEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new formal_parameter_listEnd1(cloneProperties(),firstToken,lastToken);
  }
  public fixed_parameter getFixed_parameter() {
    return ((PropertyOne<fixed_parameter>)getProperty("fixed_parameter")).getValue();
  }
  public ArrayList<fixed_parameterEnd> getFixed_parameterEnd() {
    return ((PropertyZeroOrMore<fixed_parameterEnd>)getProperty("fixed_parameterEnd")).getValue();
  }
}
