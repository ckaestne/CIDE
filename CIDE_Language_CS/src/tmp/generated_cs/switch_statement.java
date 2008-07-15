package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class switch_statement extends GenASTNode {
  public switch_statement(expression expression, switch_block switch_block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<expression>("expression", expression),
      new PropertyOne<switch_block>("switch_block", switch_block)
    }, firstToken, lastToken);
  }
  public switch_statement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new switch_statement(cloneProperties(),firstToken,lastToken);
  }
  public expression getExpression() {
    return ((PropertyOne<expression>)getProperty("expression")).getValue();
  }
  public switch_block getSwitch_block() {
    return ((PropertyOne<switch_block>)getProperty("switch_block")).getValue();
  }
}
