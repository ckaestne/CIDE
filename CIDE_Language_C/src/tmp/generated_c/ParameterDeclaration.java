package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ParameterDeclaration extends GenASTNode {
  public ParameterDeclaration(DeclarationSpecifiers declarationSpecifiers, ParameterDeclarationInternal parameterDeclarationInternal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<DeclarationSpecifiers>("declarationSpecifiers", declarationSpecifiers),
      new PropertyOne<ParameterDeclarationInternal>("parameterDeclarationInternal", parameterDeclarationInternal)
    }, firstToken, lastToken);
  }
  public ParameterDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ParameterDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public DeclarationSpecifiers getDeclarationSpecifiers() {
    return ((PropertyOne<DeclarationSpecifiers>)getProperty("declarationSpecifiers")).getValue();
  }
  public ParameterDeclarationInternal getParameterDeclarationInternal() {
    return ((PropertyOne<ParameterDeclarationInternal>)getProperty("parameterDeclarationInternal")).getValue();
  }
}
