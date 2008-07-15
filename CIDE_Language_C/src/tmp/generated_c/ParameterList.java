package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ParameterList extends GenASTNode {
  public ParameterList(ParameterDeclaration parameterDeclaration, ArrayList<ParameterDeclaration> parameterDeclaration1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ParameterDeclaration>("parameterDeclaration", parameterDeclaration),
      new PropertyZeroOrMore<ParameterDeclaration>("parameterDeclaration1", parameterDeclaration1)
    }, firstToken, lastToken);
  }
  public ParameterList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ParameterList(cloneProperties(),firstToken,lastToken);
  }
  public ParameterDeclaration getParameterDeclaration() {
    return ((PropertyOne<ParameterDeclaration>)getProperty("parameterDeclaration")).getValue();
  }
  public ArrayList<ParameterDeclaration> getParameterDeclaration1() {
    return ((PropertyZeroOrMore<ParameterDeclaration>)getProperty("parameterDeclaration1")).getValue();
  }
}
