import {Component} from '@angular/core';
import {
  PoBreadcrumb,
  PoMenuItem,
  PoNotificationService,
  PoTagType,
  PoToolbarAction,
  PoToolbarProfile
} from '@po-ui/ng-components';
import {Router} from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  readonly filter: boolean = true;
  readonly appName: string = 'Romaneio'
  readonly menus: Array<PoMenuItem> = [
    {label: 'Dashboard', action: this.onClick.bind(this), icon: 'po-icon-home', shortLabel: 'Dashboard'},
    {label: 'Produção', action: this.onClick.bind(this), icon: 'po-icon-manufacture', shortLabel: 'Produção'}
  ];

  profile: PoToolbarProfile = {
    avatar: 'https://via.placeholder.com/48x48?text=AVATAR',
    subtitle: 'dev@email.com.br',
    title: 'Renato Justo'
  };

  profileActions: Array<PoToolbarAction> = [
    {icon: 'po-icon-settings', label: 'Configurações', action: (item: PoToolbarAction) => this.showAction(item)},
    {
      icon: 'po-icon-exit',
      label: 'Sair',
      type: 'danger',
      separator: true,
      action: (item: PoToolbarAction) => this.showAction(item)
    }
  ];

  actions: Array<PoToolbarAction> = [
    {label: 'Start', action: (item: any) => this.showAction(item)},
    {label: 'Finalize', action: (item: PoToolbarAction) => this.showAction(item)}
  ];

  birthDate: string = '26/12/1978';
  email: string = 'john.doe@po-ui.com.br';
  fathersName: string = 'Mike Doe';
  genre: string = 'male';
  graduation: string = 'College Degree';
  mothersName: string = 'Jane Doe';
  name: string = 'John Doe';
  nationality: string = 'USA';
  nickname: string = 'Big John';
  placeOfBirth: string = 'Colorado';
  statusValue: string = 'Success';
  userId: string = '122635';
  success: PoTagType = PoTagType.Success

  public readonly breadcrumb: PoBreadcrumb = {
    items: [{ label: 'Início', link: '/' }, { label: 'Dashboard' }]
  };

  constructor(private poNotification: PoNotificationService, private router: Router) {
  }

  onClick() {
    alert('Clicked in menu item');
  }

  onClickNotification(item: PoToolbarAction) {
    window.open('https://github.com/po-ui/po-angular/blob/master/CHANGELOG.md', '_blank');
    item.type = 'default';
  }

  openDialog(item: PoToolbarAction) {
    item.type = 'default';
  }

  showAction(item: PoToolbarAction): void {
    if (item.label === 'Sair') {
      this.router.navigate(['']);
    } else {
      this.poNotification.success(`Action clicked: ${item.label}`);
    }
  }
}
