package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class OptionsNode extends GenASTNode {
  public OptionsNode(ASTStringNode _options, Block block, ASTStringNode _options1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("_options", _options),
      new PropertyOne<Block>("block", block),
      new PropertyOne<ASTStringNode>("_options1", _options1)
    }, firstToken, lastToken);
  }
  public OptionsNode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new OptionsNode(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode get_options() {
    return ((PropertyOne<ASTStringNode>)getProperty("_options")).getValue();
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
  public ASTStringNode get_options1() {
    return ((PropertyOne<ASTStringNode>)getProperty("_options1")).getValue();
  }
}
