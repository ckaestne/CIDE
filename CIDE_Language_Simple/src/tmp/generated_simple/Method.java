package tmp.generated_simple;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Method extends GenASTNode {
  public Method(Name name, Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Name>("name", name),
      new PropertyOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public Method(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Method(cloneProperties(),firstToken,lastToken);
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
}
