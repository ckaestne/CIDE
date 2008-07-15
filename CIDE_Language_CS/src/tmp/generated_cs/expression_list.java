package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expression_list extends GenASTNode {
  public expression_list(expression expression, ArrayList<expression_listList> expression_listList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expression>("expression", expression),
      new PropertyZeroOrMore<expression_listList>("expression_listList", expression_listList)
    }, firstToken, lastToken);
  }
  public expression_list(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expression_list(cloneProperties(),firstToken,lastToken);
  }
  public expression getExpression() {
    return ((PropertyOne<expression>)getProperty("expression")).getValue();
  }
  public ArrayList<expression_listList> getExpression_listList() {
    return ((PropertyZeroOrMore<expression_listList>)getProperty("expression_listList")).getValue();
  }
}
