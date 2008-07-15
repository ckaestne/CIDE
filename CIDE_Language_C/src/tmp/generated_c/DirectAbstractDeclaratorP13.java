package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DirectAbstractDeclaratorP13 extends DirectAbstractDeclaratorP1 {
  public DirectAbstractDeclaratorP13(ParameterTypeList parameterTypeList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ParameterTypeList>("parameterTypeList", parameterTypeList)
    }, firstToken, lastToken);
  }
  public DirectAbstractDeclaratorP13(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DirectAbstractDeclaratorP13(cloneProperties(),firstToken,lastToken);
  }
  public ParameterTypeList getParameterTypeList() {
    return ((PropertyZeroOrOne<ParameterTypeList>)getProperty("parameterTypeList")).getValue();
  }
}
