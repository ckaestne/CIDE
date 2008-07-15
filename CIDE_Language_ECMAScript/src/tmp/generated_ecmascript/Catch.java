package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Catch extends GenASTNode {
  public Catch(Identifier identifier, Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Identifier>("identifier", identifier),
      new PropertyOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public Catch(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Catch(cloneProperties(),firstToken,lastToken);
  }
  public Identifier getIdentifier() {
    return ((PropertyOne<Identifier>)getProperty("identifier")).getValue();
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
}
