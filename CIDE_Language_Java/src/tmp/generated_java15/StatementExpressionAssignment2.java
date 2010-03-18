package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StatementExpressionAssignment2 extends StatementExpressionAssignment {
  public StatementExpressionAssignment2(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public StatementExpressionAssignment2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StatementExpressionAssignment2(cloneProperties(),firstToken,lastToken);
  }
}
