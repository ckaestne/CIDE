package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Declaration extends GenASTNode {
  public Declaration(DeclarationSpecifiers declarationSpecifiers, InitDeclaratorList initDeclaratorList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<DeclarationSpecifiers>("declarationSpecifiers", declarationSpecifiers),
      new PropertyZeroOrOne<InitDeclaratorList>("initDeclaratorList", initDeclaratorList)
    }, firstToken, lastToken);
  }
  public Declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Declaration(cloneProperties(),firstToken,lastToken);
  }
  public DeclarationSpecifiers getDeclarationSpecifiers() {
    return ((PropertyOne<DeclarationSpecifiers>)getProperty("declarationSpecifiers")).getValue();
  }
  public InitDeclaratorList getInitDeclaratorList() {
    return ((PropertyZeroOrOne<InitDeclaratorList>)getProperty("initDeclaratorList")).getValue();
  }
}
