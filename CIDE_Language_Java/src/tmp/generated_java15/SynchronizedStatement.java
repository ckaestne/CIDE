package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SynchronizedStatement extends GenASTNode {
  public SynchronizedStatement(Expression expression, Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Expression>("expression", expression),
      new PropertyOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public SynchronizedStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SynchronizedStatement(cloneProperties(),firstToken,lastToken);
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
}
