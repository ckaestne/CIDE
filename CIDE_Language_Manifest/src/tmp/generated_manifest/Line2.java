package tmp.generated_manifest;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Line2 extends Line {
  public Line2(Export export, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Export>("export", export)
    }, firstToken, lastToken);
  }
  public Line2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Line2(cloneProperties(),firstToken,lastToken);
  }
  public Export getExport() {
    return ((PropertyOne<Export>)getProperty("export")).getValue();
  }
}
