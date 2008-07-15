package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class expansion_unit2 extends expansion_unit {
  public expansion_unit2(Block block, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Block>("block", block)
    }, firstToken, lastToken);
  }
  public expansion_unit2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new expansion_unit2(cloneProperties(),firstToken,lastToken);
  }
  public Block getBlock() {
    return ((PropertyOne<Block>)getProperty("block")).getValue();
  }
}
