package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssignmentOperator10 extends AssignmentOperator {
  public AssignmentOperator10(ASTStringNode asstil, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("asstil", asstil)
    }, firstToken, lastToken);
  }
  public AssignmentOperator10(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssignmentOperator10(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAsstil() {
    return ((PropertyOne<ASTStringNode>)getProperty("asstil")).getValue();
  }
}
