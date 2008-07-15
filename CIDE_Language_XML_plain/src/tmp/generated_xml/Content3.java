package tmp.generated_xml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Content3 extends Content {
  public Content3(CDSect cDSect, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<CDSect>("cDSect", cDSect)
    }, firstToken, lastToken);
  }
  public Content3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Content3(cloneProperties(),firstToken,lastToken);
  }
  public CDSect getCDSect() {
    return ((PropertyOne<CDSect>)getProperty("cDSect")).getValue();
  }
}
