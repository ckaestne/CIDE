package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class InitDeclaratorList extends GenASTNode {
  public InitDeclaratorList(InitDeclarator initDeclarator, ArrayList<InitDeclarator> initDeclarator1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<InitDeclarator>("initDeclarator", initDeclarator),
      new PropertyZeroOrMore<InitDeclarator>("initDeclarator1", initDeclarator1)
    }, firstToken, lastToken);
  }
  public InitDeclaratorList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new InitDeclaratorList(cloneProperties(),firstToken,lastToken);
  }
  public InitDeclarator getInitDeclarator() {
    return ((PropertyOne<InitDeclarator>)getProperty("initDeclarator")).getValue();
  }
  public ArrayList<InitDeclarator> getInitDeclarator1() {
    return ((PropertyZeroOrMore<InitDeclarator>)getProperty("initDeclarator1")).getValue();
  }
}
