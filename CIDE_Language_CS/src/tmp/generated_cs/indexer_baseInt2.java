package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class indexer_baseInt2 extends indexer_baseInt {
  public indexer_baseInt2(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public indexer_baseInt2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new indexer_baseInt2(cloneProperties(),firstToken,lastToken);
  }
}
