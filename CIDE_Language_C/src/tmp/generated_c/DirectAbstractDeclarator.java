package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DirectAbstractDeclarator extends GenASTNode {
  public DirectAbstractDeclarator(DirectAbstractDeclaratorP1 directAbstractDeclaratorP1, ArrayList<DirectAbstractDeclaratorP2> directAbstractDeclaratorP2, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<DirectAbstractDeclaratorP1>("directAbstractDeclaratorP1", directAbstractDeclaratorP1),
      new PropertyZeroOrMore<DirectAbstractDeclaratorP2>("directAbstractDeclaratorP2", directAbstractDeclaratorP2)
    }, firstToken, lastToken);
  }
  public DirectAbstractDeclarator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DirectAbstractDeclarator(cloneProperties(),firstToken,lastToken);
  }
  public DirectAbstractDeclaratorP1 getDirectAbstractDeclaratorP1() {
    return ((PropertyOne<DirectAbstractDeclaratorP1>)getProperty("directAbstractDeclaratorP1")).getValue();
  }
  public ArrayList<DirectAbstractDeclaratorP2> getDirectAbstractDeclaratorP2() {
    return ((PropertyZeroOrMore<DirectAbstractDeclaratorP2>)getProperty("directAbstractDeclaratorP2")).getValue();
  }
}
