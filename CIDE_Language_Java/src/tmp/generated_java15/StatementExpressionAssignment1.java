package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StatementExpressionAssignment1 extends StatementExpressionAssignment {
  public StatementExpressionAssignment1(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public StatementExpressionAssignment1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StatementExpressionAssignment1(cloneProperties(),firstToken,lastToken);
  }
}
