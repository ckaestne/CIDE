package tmp.generated_simple;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IfStatement extends GenASTNode {
  public IfStatement(Expression expression, Block block, Block block1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression", expression),
      new PropertyOne<Block>("block", block),
      new PropertyZeroOrOne<Block>("block1", block1)
    }, firstToken, lastToken);
  }
  public IfStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IfStatement(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
  public Block getBlock1() {
    return ((PropertyZeroOrOne<Block>)getProperty("block1")).getValue();
  }
}
