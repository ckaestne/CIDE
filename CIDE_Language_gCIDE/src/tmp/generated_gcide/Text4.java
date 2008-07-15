package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Text4 extends Text {
  public Text4(LayoutHint layoutHint, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<LayoutHint>("layoutHint", layoutHint)
    }, firstToken, lastToken);
  }
  public Text4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Text4(cloneProperties(),firstToken,lastToken);
  }
  public LayoutHint getLayoutHint() {
    return ((PropertyOne<LayoutHint>)getProperty("layoutHint")).getValue();
  }
}
