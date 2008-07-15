package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ExternDecl extends GenASTNode {
  public ExternDecl(ASTStringNode literal, Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("literal", literal),
      new PropertyOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public ExternDecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ExternDecl(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getLiteral() {
    return ((PropertyOne<ASTStringNode>)getProperty("literal")).getValue();
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
}
