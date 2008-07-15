package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DirectDeclarator extends GenASTNode {
  public DirectDeclarator(DirectDeclaratorP1 directDeclaratorP1, ArrayList<DirectDeclaratorP2> directDeclaratorP2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<DirectDeclaratorP1>("directDeclaratorP1", directDeclaratorP1),
      new PropertyZeroOrMore<DirectDeclaratorP2>("directDeclaratorP2", directDeclaratorP2)
    }, firstToken, lastToken);
  }
  public DirectDeclarator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DirectDeclarator(cloneProperties(),firstToken,lastToken);
  }
  public DirectDeclaratorP1 getDirectDeclaratorP1() {
    return ((PropertyOne<DirectDeclaratorP1>)getProperty("directDeclaratorP1")).getValue();
  }
  public ArrayList<DirectDeclaratorP2> getDirectDeclaratorP2() {
    return ((PropertyZeroOrMore<DirectDeclaratorP2>)getProperty("directDeclaratorP2")).getValue();
  }
}
