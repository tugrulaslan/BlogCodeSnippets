package com.tugrulaslan.repository;

import com.tugrulaslan.entity.Host;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HostRepositoryTest {

    @Autowired
    private HostRepository repository;

    @Test
    public void shouldFindPolishHostFromSecureEuropeanHostsRussianRoulette() {
        //given
        final String expectedAddress = "https://ftp.icm.edu.pl/pub/Linux/fedora/linux/epel/7/x86_64/";

        //when
        final List<Host> hosts = repository.findByAddressContaining("http");

        //then
        assertThat(hosts).isNotNull();
        assertThat(hosts.get(2).getAddress()).isEqualTo(expectedAddress);
    }

    @Test
    public void shouldFindPolishHostFromSecureEuropeanHostsNonRussianRoulette() {
        //given
        final String expectedAddress = "https://ftp.icm.edu.pl/pub/Linux/fedora/linux/epel/7/x86_64/";

        //when
        final Host host = repository.findByAddress(expectedAddress);

        //then
        assertThat(host).isNotNull();
        assertThat(host.getAddress()).isEqualTo(expectedAddress);
    }

    @Test
    public void shouldFindAllSecureEuropeanHostsRussianRoulette() {
        //given-when
        final List<Host> hosts = repository.findByAddressContaining("https");

        //then
        assertThat(hosts).isNotNull();
        assertThat(hosts.get(0).getAddress()).isEqualTo("https://mirror.karneval.cz/pub/linux/fedora/epel/7/x86_64/");
        assertThat(hosts.get(1).getAddress()).isEqualTo("https://ftp.icm.edu.pl/pub/Linux/fedora/linux/epel/7/x86_64/");
        assertThat(hosts.get(2).getAddress()).isEqualTo("https://ftp-stud.hs-esslingen.de/pub/epel/7/x86_64/");
    }

    @Test
    public void shouldFindAllSecureEuropeanHostsNonRussianRoulette() {
        //given
        ArrayList<String> expectedHosts = new ArrayList<>(Arrays.asList("https://mirror.karneval.cz/pub/linux/fedora/epel/7/x86_64/",
                "https://ftp.icm.edu.pl/pub/Linux/fedora/linux/epel/7/x86_64/",
                "https://ftp-stud.hs-esslingen.de/pub/epel/7/x86_64/"));
        //when
        final List<Host> hosts = repository.findByAddressContaining("https");

        //then
        final List<String> hostNames = hosts.stream().map(Host::getAddress).collect(Collectors.toList());
        assertThat(hosts).isNotNull();
        assertThat(hostNames).isNotNull();
        assertThat(hostNames).containsAnyElementsOf(expectedHosts);
    }
}
