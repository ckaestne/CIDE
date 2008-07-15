package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CatchPart extends GenASTNode {
  public CatchPart(Name name, ASTStringNode identifier, Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Name>("name", name),
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public CatchPart(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CatchPart(cloneProperties(),firstToken,lastToken);
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
}
