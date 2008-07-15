package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DirectAbstractDeclaratorP22 extends DirectAbstractDeclaratorP2 {
  public DirectAbstractDeclaratorP22(ParameterTypeList parameterTypeList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ParameterTypeList>("parameterTypeList", parameterTypeList)
    }, firstToken, lastToken);
  }
  public DirectAbstractDeclaratorP22(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DirectAbstractDeclaratorP22(cloneProperties(),firstToken,lastToken);
  }
  public ParameterTypeList getParameterTypeList() {
    return ((PropertyZeroOrOne<ParameterTypeList>)getProperty("parameterTypeList")).getValue();
  }
}
