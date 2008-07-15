package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ParameterTypeList extends GenASTNode {
  public ParameterTypeList(ParameterList parameterList, ASTTextNode text18, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ParameterList>("parameterList", parameterList),
      new PropertyZeroOrOne<ASTTextNode>("text18", text18)
    }, firstToken, lastToken);
  }
  public ParameterTypeList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ParameterTypeList(cloneProperties(),firstToken,lastToken);
  }
  public ParameterList getParameterList() {
    return ((PropertyOne<ParameterList>)getProperty("parameterList")).getValue();
  }
  public ASTTextNode getText18() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text18")).getValue();
  }
}
