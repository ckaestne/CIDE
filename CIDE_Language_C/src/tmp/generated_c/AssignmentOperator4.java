package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AssignmentOperator4 extends AssignmentOperator {
  public AssignmentOperator4(ASTStringNode asspercent, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("asspercent", asspercent)
    }, firstToken, lastToken);
  }
  public AssignmentOperator4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AssignmentOperator4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getAsspercent() {
    return ((PropertyOne<ASTStringNode>)getProperty("asspercent")).getValue();
  }
}
