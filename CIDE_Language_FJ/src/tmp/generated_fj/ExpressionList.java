package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ExpressionList extends GenASTNode {
  public ExpressionList(ArrayList<Expression> expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<Expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public ExpressionList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ExpressionList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<Expression> getExpression() {
    return ((PropertyList<Expression>)getProperty("expression")).getValue();
  }
}
