package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DirectDeclaratorP22 extends DirectDeclaratorP2 {
  public DirectDeclaratorP22(ParameterTypeList parameterTypeList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ParameterTypeList>("parameterTypeList", parameterTypeList)
    }, firstToken, lastToken);
  }
  public DirectDeclaratorP22(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DirectDeclaratorP22(cloneProperties(),firstToken,lastToken);
  }
  public ParameterTypeList getParameterTypeList() {
    return ((PropertyOne<ParameterTypeList>)getProperty("parameterTypeList")).getValue();
  }
}
