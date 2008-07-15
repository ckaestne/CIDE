package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class regexpr_spec extends GenASTNode {
  public regexpr_spec(regular_expression regular_expression, Block block, ASTStringNode identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<regular_expression>("regular_expression", regular_expression),
      new PropertyZeroOrOne<Block>("block", block),
      new PropertyZeroOrOne<ASTStringNode>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public regexpr_spec(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new regexpr_spec(cloneProperties(),firstToken,lastToken);
  }
  public regular_expression getRegular_expression() {
    return ((PropertyOne<regular_expression>)getProperty("regular_expression")).getValue();
  }
  public Block getBlock() {
    return ((PropertyZeroOrOne<Block>)getProperty("block")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
}
